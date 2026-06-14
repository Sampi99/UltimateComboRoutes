import React from 'react';
import {FormattedMessage} from 'react-intl';

import {Link} from 'react-router-dom';

const AddComboButton= ({id}) => {

    return (
        <Link to={`/combos/${id}/addCombo`}>
            <button><FormattedMessage id="Añadir combo"/></button>
        </Link>
    )
} 

export default AddComboButton;