import React from 'react';
import PropTypes from 'prop-types';

import {Link} from 'react-router-dom';

const CharacterLink = ({id, name}) => {

    return (
        <Link to={`/smashCharacters/smashCharacterDetails/${id}`}>
            {name}
        </Link>
    )
} 

CharacterLink.propTypes = {
    id: PropTypes.number.isRequired,
    name:PropTypes.string.isRequired,
};

export default CharacterLink;