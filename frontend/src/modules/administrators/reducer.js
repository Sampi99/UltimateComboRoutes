import {combineReducers} from 'redux';

import * as actionTypes from './actionTypes';

const initialState = {
    admin: null,
};

const admin = (state = initialState.admin, action) => {

    switch (action.type) {

        case actionTypes.SIGN_UP_COMPLETED:
            return action.authenticatedAdmin.admin;

        case actionTypes.LOGIN_COMPLETED:
            return action.authenticatedAdmin.admin;

        case actionTypes.LOGOUT:
            return initialState.admin;

        case actionTypes.UPDATE_PROFILE_COMPLETED:
            return action.admin;

        default:
            return state;

    }

}

const reducer = combineReducers({
    admin
});

export default reducer;