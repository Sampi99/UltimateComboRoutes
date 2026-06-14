import * as actionTypes from './actionTypes';
import backend from '../../backend';

export const comboAdditionCompleted = (combo) => ({
    type: actionTypes.COMBO_ADDITION_COMPLETED,
    combo
});

export const addCombo = (characterId, secuence, difficulty, totalDamage, demo, onSuccess, 
    onErrors) => dispatch => backend.comboService.addCombo(characterId, secuence, difficulty, totalDamage, 
        demo, combo => {
            dispatch(comboAdditionCompleted(combo));
            onSuccess();
        },
            onErrors);
    
export const showCharacterCombosCompleted = (combos) => ({
    type: actionTypes.SHOW_CHARACTER_COMBOS_COMPLETED,
    combos: combos
});

export const clearCombos = () => ({
    type: actionTypes.CLEAR_COMBOS,
});

export const showcCharacterCombos= (id) => dispatch => {
    dispatch(clearCombos());
    backend.comboService.showCharacterCombos(id, result => 
        dispatch(showCharacterCombosCompleted(result)))
}; 

export const editComboCompleted = (combo) => ({
    type: actionTypes.COMBO_EDITION_COMPLETED,
    combo
});

export const editCombo = (id, secuence, difficulty, totalDamage, demo, onSuccess, 
    onErrors) => dispatch => backend.comboService.editCombo(id,
        secuence, difficulty, totalDamage, demo, combo => {
            dispatch(editComboCompleted(combo));
            onSuccess();
        },
            onErrors);


export const filterByDifficultyCompleted = (combos) => ({
    type: actionTypes.FILTER_BY_DIFFICULTY_COMPLETED,
    combos: combos
})

export const filterByDifficulty = criteria => dispatch => {
    dispatch(clearCombos());
    backend.comboService.filterByDifficulty(criteria, 
        result => dispatch(filterByDifficultyCompleted(result)))
}; 

