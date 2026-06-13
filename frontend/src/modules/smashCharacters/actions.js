import * as actionTypes from './actionTypes';
import backend from '../../backend';

export const characterUploadCompleted = (smashCharacter) => ({
    type: actionTypes.CHARACTER_UPLOAD_COMPLETED,
    smashCharacter
});

export const uploadSmashCharacter = (name, description, weight, gravity, render,
    onSuccess, onErrors) => dispatch => backend.smashCharacterService.uploadSmashCharacter(
        name, description, weight, gravity, render, smashCharacter => {
            dispatch(characterUploadCompleted(smashCharacter));
            onSuccess();
        },
            onErrors);
    
export const showSmashCharactersList = (smashCharacters) => ({
    type: actionTypes.SHOW_CHARACTER_LIST_COMPLETED,
    smashCharacters: smashCharacters
});

export const clearCharacters = () => ({
    type: actionTypes.CLEAR_LIST,
});

export const showSmashCharacters = () => dispatch =>
    dispatch(clearCharacters());
    backend.smashCharacterService.showSmashCharacters(smashCharacters => 
        dispatch(showSmashCharactersList(smashCharacters))
    );

export const updateSmashCharacterCompleted = (smashCharacter) => ({
    type: actionTypes.UPDATE_DATA_COMPLETED,
    smashCharacter
});

export const updateSmashCharacterData = (id, name, description, weight, gravity, render,
    onSuccess, onErrors) => dispatch => backend.smashCharacterService.updateSmashCharacter(id,
        name, description, weight, gravity, render, smashCharacter => {
            dispatch(updateSmashCharacterCompleted(smashCharacter));
            onSuccess();
        },
            onErrors);

export const showSmashCharacterDetailsCompleted = (smashCharacter) => ({
    type: actionTypes.SHOW_CHARACTER_DATA_COMPLETED,
    smashCharacter
});

export const clearVideogame = () => ({
    type: actionTypes.CLEAR_VIDEOGAME
});

export const showSmashCharacterDetails = id => dispatch => 
    backend.smashCharacterService.showSmashCharacterDetails(id, smashCharacter =>
        dispatch(showSmashCharacterDetailsCompleted(smashCharacter)));

export const showSmashCharacterDetails = (id, onSuccess, onErrors) => dispatch => 
    backend.smashCharacterService.showSmashCharacterDetails(id, smashCharacter => {
        dispatch(showSmashCharacterDetailsCompleted(smashCharacter));
        onSuccess(smashCharacter);
    },
        onErrors);

export const filterByNameCompleted = (smashCharacters) => ({
    type: actionTypes.FILTER_BY_NAME_COMPLETED,
    smashCharacters: smashCharacters
})

export const filterByName = criteria => dispatch => {
    dispatch(clearCharacters());
    backend.smashCharacterService.filterByName(criteria, 
        result => dispatch(showMyLibraryCompleted({criteria, result})))
}; 

