import React from 'react';
import {FormattedMessage} from 'react-intl';
import { useSelector } from 'react-redux';
import * as selectors from '../selectors';

const CharacterUploadCompleted = () => { 
    
    const smashCharacter = useSelector(selectors.getLastSmashCharacter);
    
    if(!smashCharacter) {
        return null;
    }

	return (
        <div className="alert alert-success" role="alert">
            <FormattedMessage id="El personaje se ha registrado con éxito" />
            &nbsp;
            {smashCharacter.id}
        </div>
    );

}

export default CharacterUploadCompleted; 