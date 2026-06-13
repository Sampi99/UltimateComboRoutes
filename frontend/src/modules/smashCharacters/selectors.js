const getModuleState = state => state.smashCharacters;

export const getSmashCharacter= state =>
    getModuleState(state).smashCharacter;

export const getSmashCharacters = state =>
    getModuleState(state).smashCharacters;

export const getLastSmashCharacter = state =>
    getModuleState(state).lastSmashCharater;