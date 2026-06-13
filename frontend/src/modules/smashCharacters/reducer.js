import {combineReducers} from 'redux';

import * as actionTypes from './actionTypes';

const initialState = {
    smashCharacter: null,
    smashCharacters: null,
    lastSmashCharacter: null,
};

const smashCharacter = (state = initialState.smashCharacter, action = {}) => {

    switch (action.type) {

        case actionTypes.SHOW_CHARACTER_DATA_COMPLETED:
            return action.smashCharacter;

        case actionTypes.CLEAR_CHARACTER:
            return initialState.smashCharacter;

        case actionTypes.UPDATE_DATA_COMPLETED:
            return action.smashCharacter;

        default:
            return state;

    }

}

const smashCharacters = (state = initialState.smashCharacters, action = {}) => {

    switch (action.type) {

        case actionTypes.SHOW_CHARACTER_LIST_COMPLETED:
            return action.smashCharacters;

        case actionTypes.FILTER_BY_NAME_COMPLETED:
            return action.smashCharacters;

        case actionTypes.CLEAR_LIST:
            return initialState.smashCharacter;

        default:
            return state;
    }
}

const lastSmashCharacter = (state = initialState.lastSmashCharacter, action = {}) => {

    switch (action.type) {

        case actionTypes.CHARACTER_UPLOAD_COMPLETED:
            return action.smashCharacter;

        default:
            return state;
    }
}

const reducer = combineReducers({
    smashCharacter,
    smashCharacters,
    lastSmashCharacter
});

export default reducer;