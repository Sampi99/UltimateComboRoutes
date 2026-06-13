import React, {useState, useEffect} from 'react';
import {useDispatch, useSelector} from 'react-redux';
import {useHistory, useParams} from 'react-router-dom';
import {FormattedMessage} from 'react-intl';

import {Errors} from '../../common';
import * as actions from '../actions';
import * as selectors from '../selectors';
import smashCharacters from '..';

const UpdateSmashCharacterData = () => {

    const smashCharacter = useSelector(selectors.getSmashCharacter);
    const { id } = useParams();
    const dispatch = useDispatch();
    const history = useHistory();
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');
    const [weight, setWeight] = useState('');
    const [gravity, setGravity] = useState('');
    const [render, setRender] = useState('');
    const [backendErrors, setBackendErrors] = useState(null);
    let form;

    const setCharacterDetails = (smashCharacter) => {
        setName(smashCharacter.name);
        setDescription(smashCharacter.description);
        setWeight(smashCharacter.weight);
        setGravity(smashCharacter.gravity);
        setRender(smashCharacter.render);

    }
    useEffect (()=> {
        const smashCharacterId = Number(id);
        
        if (!Number.isNaN(smashCharacterId)) {
            dispatch(actions.showSmashCharacterDetails2(smashCharacterId, (smashCharacter) => setCharacterDetails(smashCharacter)));
        }
    }, []);

    const handleSubmit = event => {

        event.preventDefault();

        if (form.checkValidity()) {
            
            dispatch(actions.updateSmashCharacterData(smashCharacter.id, name, description, weight, gravity, render, 
                () => history.push('/smashCharacters/characterUpdateCompleted'),
                errors => setBackendErrors(errors)
            ));
            

        } else {

            setBackendErrors(null);
            form.classList.add('was-validated');

        }

    }

    return (
        <div>
            <Errors errors={backendErrors} onClose={() => setBackendErrors(null)}/>
            <div className="card bg-light border-dark">
                <h5 className="card-header">
                    <FormattedMessage id="Registro de administradores"/>
                </h5>
                <div className="card-body">
                    <form ref={node => form = node}
                        className="needs-validation" noValidate 
                        onSubmit={e => handleSubmit(e)}>
                        <div className="form-group row py-sm-1">
                            <label htmlFor="name" className="col-md-3 col-form-label">
                                <FormattedMessage id="Nombre del personaje"/>
                            </label>
                            <div className="col-md-4">
                                <input type="text" id="name" className="form-control"
                                    value={name}
                                    onChange={e => setName(e.target.value)}
                                    autoFocus
                                    required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row py-sm-1">
                            <label htmlFor="description" className="col-md-3 col-form-label">
                                <FormattedMessage id="Descripción"/>
                            </label>
                            <div className="col-md-4">
                                <input type="textarea" id="description" className="form-control"
                                    value={description}
                                    onChange={e => setDescription(e.target.value)}
                                    required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row py-sm-1">
                            <label htmlFor="weight" className="col-md-3 col-form-label">
                                <FormattedMessage id="Peso"/>
                            </label>
                            <div className="col-md-4">
                                <input type="number" id="weight" className="form-control"
                                    value={weight}
                                    onChange={e => setWeight(e.target.value)}
                                    required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row py-sm-1">
                            <label htmlFor="gravity" className="col-md-3 col-form-label">
                                <FormattedMessage id="Gravedad / velocidad de caída"/>
                            </label>
                            <div className="col-md-4">
                                <input type="text" id="gravity" className="form-control"
                                    value={gravity}
                                    onChange={e => setGravity(e.target.value)}
                                    required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row py-sm-1">
                            <label htmlFor="render" className="col-md-3 col-form-label">
                                <FormattedMessage id="Render"/>
                            </label>
                            <div className="col-md-4">
                                <input type="text" id="render" className="form-control"
                                    value={render}
                                    onChange={e => setRender(e.target.value)}
                                    required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row py-sm-2">
                            <div className="offset-md-3 col-md-2">
                                <button type="submit" className="btn btn-primary">
                                    <FormattedMessage id="Confirmar"/>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );

}

export default UpdateSmashCharacterData;