import React, {useState} from 'react';
import {useDispatch, useSelector} from 'react-redux';
import {useHistory} from 'react-router-dom';
import {FormattedMessage} from 'react-intl';

import {Errors} from '../../common';
import * as actions from '../actions';
import * as selectors from '../selectors';

const Login = () => {

    const admin = useSelector(selectors.getAdmin);
    const dispatch = useDispatch();
    const history = useHistory();
    const [oldPassword, setOldPassword] = useState('');
    const [newPassword, setPassword] = useState('');
    const [backendErrors, setBackendErrors] = useState(null);
    let form;

    const handleSubmit = event => {

        event.preventDefault();

        if (form.checkValidity()) {
            
            dispatch(actions.changePassword(admin.id, oldPassword, newPassword, 
                () => history.push('/'),
                errors => setBackendErrors(errors),
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
                    <FormattedMessage id="Cambiar contraseña"/>
                </h5>
                <div className="card-body">
                    <form ref={node => form = node}
                        className="needs-validation" noValidate 
                        onSubmit={e => handleSubmit(e)}>
                        <div className="form-group row py-sm-1">
                            <label htmlFor="oldPassword" className="col-md-3 col-form-label">
                                <FormattedMessage id="Contraseña antigua"/>
                            </label>
                            <div className="col-md-4">
                                <input type="password" id="oldPassword" className="form-control"
                                    value={oldPassword}
                                    onChange={e => setOldPassword(e.target.value)}
                                    autoFocus
                                    required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row py-sm-1">
                            <label htmlFor="newPassword" className="col-md-3 col-form-label">
                                <FormattedMessage id="Contraseña"/>
                            </label>
                            <div className="col-md-4">
                                <input type="password" id="newPassword" className="form-control"
                                    value={newPassword}
                                    onChange={e => setNewPassword(e.target.value)}
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

export default ChangePassword;