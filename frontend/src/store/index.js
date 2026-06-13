import {legacy_createStore, applyMiddleware, compose} from 'redux';
import thunk from 'redux-thunk';

import rootReducer from './rootReducer';

const configureStore = () => {

    const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || 
        compose;

    const middlewares = [thunk];

    return legacy_createStore(rootReducer, composeEnhancers(
       applyMiddleware(...middlewares)));

}

export default configureStore;