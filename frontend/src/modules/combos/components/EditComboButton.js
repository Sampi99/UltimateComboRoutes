import React from 'react';
import PropTypes from 'prop-types';
import {FormattedMessage} from 'react-intl';

import {Link} from 'react-router-dom';

const EditComboButton = ({id}) => {

    return (
        <Link to={`/combos/${id}/editCombo`}>
            <button><FormattedMessage id="Editar" /></button>
        </Link>
    )
} 

EditComboButton.propTypes = {
    id: PropTypes.number.isRequired,
};

export default EditComboButton;