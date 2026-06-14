import React, {useEffect} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {FormattedMessage} from 'react-intl';
import {useParams} from 'react-router-dom';
import * as selectors from '../selectors';
import * as actions from '../actions';
import Combos from './Combos';
import AddComboButton from './AddComboButton';
import FilterByDifficulty from './FilterByDifficulty';

const ShowCharacterCombos = () => {

    const dispatch = useDispatch();
    const noLoop = 1;
    const { id } = useParams();

    const characterId = Number(id)

    useEffect(() => {
        if(!Number.isNaN(characterId)) {
        dispatch(actions.showcCharacterCombos(characterId));
        }
    },[noLoop]);
    

    const combos = useSelector(selectors.getCombos);

    if(!combos) {
        return null;
    }

    if (combos.length === 0) {
        return (
            <><AddComboButton id={characterId}/><div className="alert alert-info" role="alert"><br></br>
                <FilterByDifficulty/>
                <FormattedMessage id='Todavía no hay combos para este personaje' />
            </div></>
        );
    }

    return (
        <div>
            <AddComboButton id={characterId}/>
            <FilterByDifficulty/>
            <Combos combos={combos}/>
        </div>
    );

}

export default ShowCharacterCombos;