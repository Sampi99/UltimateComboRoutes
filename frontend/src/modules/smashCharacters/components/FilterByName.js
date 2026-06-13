import React, {useState} from 'react';
import {useDispatch} from 'react-redux';
import {useHistory} from 'react-router-dom';
import {FormattedMessage} from 'react-intl';

import * as actions from '../actions';

const FilterByName = () => {

   let form;

    const dispatch = useDispatch();
	const history = useHistory();
	const [name, setName] = useState('');

    const handleSubmit = event => {
		event.preventDefault();
        dispatch(actions.filterByName(
            {name}));
        history.push('/smashCharacters/filterByName');

    }

    return (
			<form className="form-inline mt-2 mt-md-0" ref={node => form = node}
				onSubmit={(e) => handleSubmit(e)}>
				<div className="form-row">
                    <FormattedMessage id="Busca por nombre:"/>
                    &nbsp;
					<div className="navbar-nav ms-auto">
					<input type="text" id="name" className="form-control mr-sm-2"
						value={name}
						onChange={e => setName(e.target.value)}
						autoFocus
						required />
					</div>
				</div>
			</form>
    );

}

export default FilterByName;