import {combineReducers} from 'redux';

import * as actionTypes from './actionTypes';

const initialState = {
    administrator: null
};

const administrator = (state = initialState.administrator, action) => {

    switch (action.type) {

        case actionTypes.SIGN_UP_COMPLETED:
            return action.authenticatedAdmin.administrator;

        case actionTypes.LOGIN_COMPLETED:
            return action.authenticatedAdmin.administrator;

        case actionTypes.LOGOUT:
            return initialState.administrator;

        case actionTypes.UPDATE_PROFILE_COMPLETED:
            return action.administrator;

        default:
            return state;

    }

}

const reducer = combineReducers({
    administrator
});

export default reducer;