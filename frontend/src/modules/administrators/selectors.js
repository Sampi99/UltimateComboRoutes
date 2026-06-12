const getModuleState = state => state.administrators;

export const getAdmin = state => 
    getModuleState(state).admin;

export const isLoggedIn = state =>
    getAdmin(state) !== null

export const getUsername = state => 
    isLoggedIn(state) ? getAdmin(state).username : null;

