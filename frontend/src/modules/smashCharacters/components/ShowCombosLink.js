import React from 'react';
import PropTypes from 'prop-types';
import {FormattedMessage} from 'react-intl';

import {Link} from 'react-router-dom';

const ShowCombosLink = ({id}) => {

    return (
        <Link to={`/combos/${id}/showCharacterCombos`}>
            <button><FormattedMessage id="Mostrar combos" /></button>
        </Link>
    )
} 

ShowCombosLink.propTypes = {
    id: PropTypes.number.isRequired,
};

export default ShowCombosLink;