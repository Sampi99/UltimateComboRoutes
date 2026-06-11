package tfc.project.ultimateComboRoutes.rest.controllers;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tfc.project.ultimateComboRoutes.model.entities.Administrator;
import tfc.project.ultimateComboRoutes.model.exceptions.DuplicateInstanceException;
import tfc.project.ultimateComboRoutes.model.exceptions.InstanceNotFoundException;
import tfc.project.ultimateComboRoutes.model.exceptions.WrongCredentialsException;
import tfc.project.ultimateComboRoutes.model.exceptions.WrongOldPasswordException;
import tfc.project.ultimateComboRoutes.model.services.AdministratorService;
import tfc.project.ultimateComboRoutes.rest.common.ErrorsDto;
import tfc.project.ultimateComboRoutes.rest.common.JwtGenerator;
import tfc.project.ultimateComboRoutes.rest.common.JwtInfo;
import tfc.project.ultimateComboRoutes.rest.dtos.AdministratorConversor;
import tfc.project.ultimateComboRoutes.rest.dtos.AdministratorDto;
import tfc.project.ultimateComboRoutes.rest.dtos.AuthenticatedAdminDto;
import tfc.project.ultimateComboRoutes.rest.dtos.ChangePasswordParamsDto;
import tfc.project.ultimateComboRoutes.rest.dtos.CredentialsDto;

@RestController
@RequestMapping("/api/administrators")
public class AdministratorController {

	private static final String WRONG_CREDENTIALS_EXCEPTION = "Las credenciales son incorrectas";

	private static final String WRONG_OLD_PASSWORD_EXCEPTION = "La contraseña antigua no es correcta";

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private JwtGenerator jwtGenerator;

	@Autowired
	private AdministratorService adminService;

	private String generateServiceToken(Administrator admin) {

		JwtInfo jwtInfo = new JwtInfo(admin.getId(), admin.getUsername());

		return jwtGenerator.generate(jwtInfo);

	}

	@ExceptionHandler(WrongCredentialsException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorsDto handleWrongCredentialsException(WrongCredentialsException exception, Locale locale) {

		String errorMessage = messageSource.getMessage(WRONG_CREDENTIALS_EXCEPTION, null, WRONG_CREDENTIALS_EXCEPTION,
				locale);

		return new ErrorsDto(errorMessage);

	}

	@ExceptionHandler(WrongOldPasswordException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorsDto handleWrongOldPasswordException(WrongOldPasswordException exception, Locale locale) {

		String errorMessage = messageSource.getMessage(WRONG_OLD_PASSWORD_EXCEPTION, null, WRONG_OLD_PASSWORD_EXCEPTION,
				locale);

		return new ErrorsDto(errorMessage);

	}

	@PostMapping("/signUp")
	public AdministratorDto signUp(@Validated @RequestBody AdministratorDto administratorDto)
			throws DuplicateInstanceException {

		Administrator admin = AdministratorConversor.toAdministrator(administratorDto);

		adminService.signUp(admin.getUsername(), admin.getPassword(), admin.getName(), admin.getSurname(),
				admin.getEmail());

		return AdministratorConversor.toAdministratorDto(admin);

	}

	@PostMapping("/login")
	public AuthenticatedAdminDto login(@Validated @RequestBody CredentialsDto credentials)
			throws WrongCredentialsException, InstanceNotFoundException {

		Administrator admin = adminService.login(credentials.getUsername(), credentials.getPassword());

		return AdministratorConversor.toAuthenticatedAdminDto(admin, generateServiceToken(admin));

	}

	@PutMapping("/{myId}")
	public AdministratorDto updateProfile(@RequestAttribute Long adminId, @PathVariable("myId") Long myId,
			@Validated @RequestBody AdministratorDto dto) throws DuplicateInstanceException, InstanceNotFoundException {

		return AdministratorConversor.toAdministratorDto(
				adminService.updateProfile(myId, dto.getUsername(), dto.getName(), dto.getSurname(), dto.getEmail()));
	}

	@PutMapping("{myId}/changePassword")
	public void changePassword(@RequestAttribute Long adminId, @PathVariable("myId") Long myId,
			@Validated @RequestBody ChangePasswordParamsDto dto)
			throws DuplicateInstanceException, InstanceNotFoundException, WrongOldPasswordException {

		adminService.changePassword(myId, dto.getOldPassword(), dto.getNewPassword());
	}
}
