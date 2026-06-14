import {combineReducers} from 'redux';

import * as actionTypes from './actionTypes';

const initialState = {
    combo: null,
    combos: null,
    lastCombo: null
};

const combo = (state = initialState.combo, action = {}) => {

    switch (action.type) {

        case actionTypes.COMBO_EDITION_COMPLETED:
            return action.combo;

        default:
            return state;

    }

}

const combos = (state = initialState.combos, action = {}) => {

    switch (action.type) {

        case actionTypes.SHOW_CHARACTER_COMBOS_COMPLETED:
            return action.combos;

        case actionTypes.FILTER_BY_DIFFICULTY_COMPLETED:
            return action.combos;

        case actionTypes.CLEAR_COMBOS:
            return initialState.combo;

        default:
            return state;
    }
}

const lastCombo = (state = initialState.lastCombo, action = {}) => {

    switch (action.type) {

        case actionTypes.COMBO_ADDITION_COMPLETED:
            return action.combo;

        default:
            return state;
    }
}

const reducer = combineReducers({
    combo,
    combos,
    lastCombo
});

export default reducer;