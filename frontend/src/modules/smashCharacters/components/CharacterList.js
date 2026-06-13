import React from 'react';
import {FormattedMessage} from 'react-intl';
import PropTypes from 'prop-types';
import smashCharacters from '..';
import {CharacterLink, UpdateCharacterButton} from '../../common'

const SmashCharacters = ({ smashCharacters }) => (

    <table className="table table-striped table-hover">

        <thead>
            <tr>
                <th scope="col">
                    <FormattedMessage id="PERSONAJES" />
                </th>
            </tr>
        </thead>

        <tbody>
            {smashCharacters.map(smashCharacter => <tr key={smashCharacter.id}>
                <td><CharacterLink id={smashCharacter.id} name={smashCharacter.name} /></td>
                <td><UpdateCharacterButton id={smashCharacter.id}/></td>
            </tr>
            )}
        </tbody>
    </table>
);

SmashCharacters.propTypes = {
    smashCharacters: PropTypes.array.isRequired
};

export default SmashCharacters;