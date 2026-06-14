import React from 'react';
import {FormattedMessage} from 'react-intl';
import { useSelector } from 'react-redux';
import * as selectors from '../selectors';

const AddComboCompleted = () => { 
    
    const combo = useSelector(selectors.getLastCombo);
    
    if(!combo) {
        return null;
    }

    return (
        <div className="alert alert-success" role="alert">
            <FormattedMessage id="El combo se ha añadido correctamente" />
            &nbsp;
            {combo.id}
        </div>
    );

}

export default AddComboCompleted; 