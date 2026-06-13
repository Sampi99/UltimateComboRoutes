import React from 'react';
import PropTypes from 'prop-types';
import {FormattedMessage} from 'react-intl';

import {Link} from 'react-router-dom';

const UpdateCharacterButton = ({id}) => {

    return (
        <Link to={`/smashCharacters/${id}/updateSmashCharacter`}>
            <button><FormattedMessage id="Editar" /></button>
        </Link>
    )
} 

UpdateCharacterButton.propTypes = {
    id: PropTypes.number.isRequired,
};

export default UpdateCharacterButton;