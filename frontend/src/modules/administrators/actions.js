import * as actionTypes from './actionTypes';
import backend from '../../backend';

export const signUpCompleted = authenticatedAdmin => ({
    type: actionTypes.SIGN_UP_COMPLETED,
    authenticatedAdmin
});

export const signUp = (admin, onSuccess, onErrors, reauthenticationCallback) => dispatch =>
    backend.administratorService.signUp(admin,
        authenticatedAdmin => {
            dispatch(signUpCompleted(authenticatedAdmin));
            onSuccess();
        },
        onErrors,
        reauthenticationCallback);

export const loginCompleted = authenticatedAdmin => ({
    type: actionTypes.LOGIN_COMPLETED,
    authenticatedAdmin
});

export const login = (username, password, onSuccess, onErrors, reauthenticationCallback) => dispatch =>
    backend.administratorService.login(username, password,
        authenticatedAdmin => {
            dispatch(loginCompleted(authenticatedAdmin));
            onSuccess();
        },
        onErrors,
        reauthenticationCallback
    );

export const tryLoginFromServiceToken = reauthenticationCallback => dispatch =>
    backend.administratorService.tryLoginFromServiceToken(
        authenticatedAdmin => {
            if (authenticatedAdmin) {
                dispatch(loginCompleted(authenticatedAdmin));
            }
        },
        reauthenticationCallback
    );
    

export const logout = () => {

    backend.administratorService.logout();

    return {type: actionTypes.LOGOUT};

};

export const updateProfileCompleted = admin => ({
    type: actionTypes.UPDATE_PROFILE_COMPLETED,
    admin
})

export const updateProfile = (admin, onSuccess, onErrors) => dispatch =>
    backend.administratorService.updateProfile(admin, 
        a => {
            dispatch(updateProfileCompleted(a));
            onSuccess();
        },
        onErrors);

export const changePassword = (id, oldPassword, newPassword, onSuccess, onErrors) => dispatch =>
    backend.administratorService.changePassword(id, oldPassword, newPassword, onSuccess, onErrors);