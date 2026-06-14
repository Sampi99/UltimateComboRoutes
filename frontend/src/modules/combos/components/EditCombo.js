import React, {useState} from 'react';
import {useDispatch, useSelector} from 'react-redux';
import {useHistory, useParams} from 'react-router-dom';
import {FormattedMessage} from 'react-intl';

import {Errors} from '../../common';
import * as actions from '../actions';
import * as selectors from '../selectors';

const EditCombo = () => {

    const { id } = useParams();
    const combo = useSelector(selectors.getCombo);
    const dispatch = useDispatch();
    const history = useHistory();
    const [secuence, setSecuence] = useState('');
    const [difficulty, setDifficulty] = useState('');
    const [totalDamage, setTotalDamage] = useState('');
    const [demo, setDemo] = useState('')
    const [backendErrors, setBackendErrors] = useState(null);
    const comboId = Number(id);
    let form;

    const handleSubmit = event => {

        event.preventDefault();

        if (form.checkValidity()) {
            if(!Number.isNaN(comboId)) {
            
                dispatch(actions.editCombo(comboId, secuence, difficulty, totalDamage, demo, 
                    () => history.push('/combos/comboEditionCompleted'),
                    errors => setBackendErrors(errors)
                ));
            }   

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
                    <FormattedMessage id="Edición de combos"/>
                </h5>
                <div className="card-body">
                    <form ref={node => form = node}
                        className="needs-validation" noValidate 
                        onSubmit={e => handleSubmit(e)}>
                        <div className="form-group row py-sm-1">
                            <label htmlFor="secuence" className="col-md-3 col-form-label">
                                <FormattedMessage id="Secuencia de ataques"/>
                            </label>
                            <div className="col-md-4">
                                <input type="text" id="secuence" className="form-control"
                                    value={secuence}
                                    onChange={e => setSecuence(e.target.value)}
                                    autoFocus
                                    required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row py-sm-1">
                            <label htmlFor="difficulty" className="col-md-3 col-form-label">
                                <FormattedMessage id="Dificultad"/>
                            </label>
                            <div className="col-md-4">
                                <input type="text" id="difficulty" className="form-control"
                                    value={difficulty}
                                    onChange={e => setDifficulty(e.target.value)}
                                    required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row py-sm-1">
                            <label htmlFor="totalDamage" className="col-md-3 col-form-label">
                                <FormattedMessage id="Daño total (%)"/>
                            </label>
                            <div className="col-md-4">
                                <input type="number" id="totalDamage" className="form-control"
                                    value={totalDamage}
                                    onChange={e => setTotalDamage(e.target.value)}
                                    required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row py-sm-1">
                            <label htmlFor="demo" className="col-md-3 col-form-label">
                                <FormattedMessage id="Demostración textual"/>
                            </label>
                            <div className="col-md-4">
                                <input type="text" id="demo" className="form-control"
                                    value={demo}
                                    onChange={e => setDemo(e.target.value)}
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

export default EditCombo;