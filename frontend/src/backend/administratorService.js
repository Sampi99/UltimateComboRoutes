import {
    fetchConfig,
    appFetch,
    setServiceToken,
    getServiceToken,
    removeServiceToken,
    setReauthenticationCallback,
} from "./appFetch";

const processLoginSignUp = (authenticatedAdmin, reauthenticationCallback, onSuccess) => {
  setServiceToken(authenticatedAdmin.serviceToken);
  setReauthenticationCallback(reauthenticationCallback);
  onSuccess(authenticatedAdmin);
}
  
export const login = (
  username,
  password,
  onSuccess,
  onErrors,
  reauthenticationCallback
) =>
  appFetch(
    "/administrators/login",
    fetchConfig("POST", { username, password }),
    (authenticatedAdmin) => {
      processLoginSignUp(authenticatedAdmin, reauthenticationCallback, onSuccess);
    },
    onErrors
  );
  
export const signUp = (admin, onSuccess, onErrors, reauthenticationCallback) => {
  appFetch(
    "/administrators/signUp",
    fetchConfig("POST", admin),
    (authenticatedAdmin) => {
      processLoginSignUp(authenticatedAdmin, reauthenticationCallback, onSuccess);
    },
    onErrors
  );
};

export const tryLoginFromServiceToken = (
  onSuccess,
  reauthenticationCallback
) => {
  const serviceToken = getServiceToken();
  
  if (!serviceToken) {
    onSuccess();
    return;
  }
  
  setReauthenticationCallback(reauthenticationCallback);
  
  appFetch(
    "/administrators/loginFromServiceToken",
    fetchConfig("POST"),
    (authenticatedAdmin) => onSuccess(authenticatedAdmin),
    () => removeServiceToken()
  );
};
  
export const logout = () => removeServiceToken();
  
export const updateProfile = (admin, onSuccess, onErrors) =>
  appFetch(`/administrators/${admin.id}`, fetchConfig("PUT", admin), onSuccess, onErrors);
  
export const changePassword = (
  id,
  oldPassword,
  newPassword,
  onSuccess,
  onErrors
) =>
  appFetch(
    `/administrators/${id}/changePassword`,
    fetchConfig("POST", { oldPassword, newPassword }),
    onSuccess,
    onErrors
  );