import {combineReducers} from 'redux';

import app from '../modules/app';
import administrators from '../modules/administrators';
import smashCharacters from '../modules/smashCharacters';


const rootReducer = combineReducers({
    app: app.reducer,
    administrators: administrators.reducer,
    smashCharacters: smashCharacters.reducer
});

export default rootReducer;