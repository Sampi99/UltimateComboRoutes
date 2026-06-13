import {combineReducers} from 'redux';

import app from '../modules/app';
import administrators from '../modules/administrators';


const rootReducer = combineReducers({
    app: app.reducer,
    administrators: administrators.reducer,
});

export default rootReducer;