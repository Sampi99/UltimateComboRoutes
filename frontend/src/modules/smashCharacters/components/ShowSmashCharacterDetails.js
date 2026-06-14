import React, {useEffect} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {FormattedMessage} from 'react-intl';
import {useParams} from 'react-router-dom';
import * as selectors from '../selectors';
import * as actions from '../actions';
import ShowCombosLink from './ShowCombosLink';

const ShowSmashCharacterDetails = () => {

	const smashCharacter = useSelector(selectors.getSmashCharacter);
    const dispatch = useDispatch();
    const { id } = useParams();

    useEffect(() => {

        const smashCharacterId = Number(id);

        if(!Number.isNaN(smashCharacterId)) {
            dispatch(actions.showSmashCharacterDetails(smashCharacterId));
        }

        return () => dispatch(actions.clearCharacter());

    }, [id, dispatch]);

    if (!smashCharacter) {
        return null;
    }

    return (
		<div>
            <ShowCombosLink id={smashCharacter.id}/>
			<div className="card text-center">
				<div className="card-body">
					<h5 className="card-title">
                        <p><strong> {smashCharacter.name}</strong></p>
                    </h5>
                    <h6 className="card-subtitle text-muted">
						<p> <FormattedMessage id='Descripción' />:&nbsp;
							{smashCharacter.description}</p>
					</h6>
                    <h6 className="card-subtitle text-muted">
						<p> <FormattedMessage id='Peso' />:&nbsp;
							{smashCharacter.weight}</p>
					</h6>
                    <h6 className="card-subtitle text-muted">
						<p> <FormattedMessage id='Gravedad / Velocidad de caída' />:&nbsp;
							{smashCharacter.gravity}</p>
					</h6>
                </div>
            </div>
        </div>
    );
}

export default ShowSmashCharacterDetails;
