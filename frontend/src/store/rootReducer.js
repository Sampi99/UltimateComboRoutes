import {combineReducers} from 'redux';

import app from '../modules/app';
import administrators from '../modules/administrators';
import smashCharacters from '../modules/smashCharacters';
import combos from '../modules/combos';


const rootReducer = combineReducers({
    app: app.reducer,
    administrators: administrators.reducer,
    smashCharacters: smashCharacters.reducer,
    combos: combos.reducer
});

export default rootReducer;