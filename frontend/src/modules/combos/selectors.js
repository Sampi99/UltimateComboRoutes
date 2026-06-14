const getModuleState = state => state.combos;

export const getCombo= state =>
    getModuleState(state).combo;

export const getCombos = state =>
    getModuleState(state).combos;

export const getLastCombo = state =>
    getModuleState(state).lastCombo;