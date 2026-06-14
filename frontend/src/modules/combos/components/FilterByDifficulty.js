import React, {useState} from 'react';
import {useDispatch} from 'react-redux';
import {useHistory, useParams} from 'react-router-dom';
import {FormattedMessage} from 'react-intl';

import * as actions from '../actions';

const FilterByDifficulty = () => {

   let form;

    const dispatch = useDispatch();
    const { id } = useParams();
    const history = useHistory();
    const [difficulty, setDifficulty] = useState('');
    const characterId = Number(id);

    const handleSubmit = event => {
        event.preventDefault();
        if(!Number.isNaN(characterId)) {
        dispatch(actions.filterByDifficulty(
            {difficulty, characterId}));
        }

    }

    return (
            <form className="form-inline mt-2 mt-md-0" ref={node => form = node}
                onSubmit={(e) => handleSubmit(e)}>
                <div className="form-row">
                    <FormattedMessage id="Busca por dificultad:"/>
                    &nbsp;
                    <div className="navbar-nav ms-auto">
                    <input type="text" id="difficulty" className="form-control mr-sm-2"
                        value={difficulty}
                        onChange={e => setDifficulty(e.target.value)}
                        autoFocus
                        required />
                    </div>
                </div>
            </form>
    );

}

export default FilterByDifficulty;