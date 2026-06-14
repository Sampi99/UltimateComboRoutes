import React from 'react';
import {FormattedMessage} from 'react-intl';
import PropTypes from 'prop-types';
import {ComboEditionButton, UpdateCharacterButton} from '../../common'
import {EditComboButton} from '..';

const Combos = ({ combos }) => (

    <table className="table table-striped table-hover">

        <thead>
            <tr>
                <th scope="col">
                    <FormattedMessage id="Secuencia" />
                </th>
                <th scope="col">
                    <FormattedMessage id="Dificultad" />
                </th>
                <th scope="col">
                    <FormattedMessage id="Daño total(%)" />
                </th>
                <th scope="col">
                    <FormattedMessage id="Demostración textual" />
                </th>
            </tr>
        </thead>

        <tbody>
            {combos.map(combo => <tr key={combo.id}>
                <td>{combo.secuence}</td>
                <td>{combo.difficulty}</td>
                <td>{combo.totalDamage}</td>
                <td>{combo.demo}</td>
                <td><EditComboButton id={combo.id}/></td>
            </tr>
            )}
        </tbody>
    </table>
);

Combos.propTypes = {
    combos: PropTypes.array.isRequired
};

export default Combos;