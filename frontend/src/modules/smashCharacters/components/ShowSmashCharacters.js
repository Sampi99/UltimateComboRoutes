import React, {useEffect} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {FormattedMessage} from 'react-intl';

import * as selectors from '../selectors';
import * as actions from '../actions';
import CharacterList from './CharacterList'
import FilterByName from './FilterByName';

const ShowSmashCharacters = () => {

    const dispatch = useDispatch();
    const noLoop = 1;

    useEffect(() => {
        dispatch(actions.showSmashCharacters());
    },[noLoop]);

    const smashCharacters = useSelector(selectors.getSmashCharacters);

    if(!smashCharacters) {
        return null;
    }

    if (smashCharacters.length === 0) {
        return (
            <><div>
                <FilterByName />
            </div><div className="alert alert-info" role="alert">
                    <FormattedMessage id='Todavía no hay personajes registrados en Ultimate Combo Routes' />
                </div></>
        );
    }

    return (
        <div>
            <FilterByName/><br></br>
            <CharacterList smashCharacters={smashCharacters}/>
        </div>
    );

}

export default ShowSmashCharacters;