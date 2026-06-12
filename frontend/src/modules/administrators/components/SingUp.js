import React, {useState} from 'react';
import {useDispatch} from 'react-redux';
import {useHistory} from 'react-router-dom';
import {FormattedMessage} from 'react-intl';

import {Errors} from '../../common';
import * as actions from '../actions';

const SignUp = () => {

    const dispatch = useDispatch();
    const history = useHistory();
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [name, setName] = useState('');
    const [surname, setSurname] = useState('');
    const [email, setEmail] = useState('');
    const [backendErrors, setBackendErrors] = useState(null);
    let form;

    const handleSubmit = event => {

        event.preventDefault();

        if (form.checkValidity()) {
            
            dispatch(actions.signUp(
                {username: username.trim(),
                password: password,
                name: name.trim(),
                surname: surname.trim(),
                email: email.trim()},
                () => history.push('/'),
                errors => setBackendErrors(errors),
                () => {
                    history.push('/administrators/signUp');
                    dispatch(actions.logout());
                }
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
                            <label htmlFor="username" className="col-md-3 col-form-label">
                                <FormattedMessage id="Nombre de usuario"/>
                            </label>
                            <div className="col-md-4">
                                <input type="text" id="username" className="form-control"
                                    value={username}
                                    onChange={e => setUsername(e.target.value)}
                                    autoFocus
                                    required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row py-sm-1">
                            <label htmlFor="password" className="col-md-3 col-form-label">
                                <FormattedMessage id="Contraseña"/>
                            </label>
                            <div className="col-md-4">
                                <input type="password" id="password" className="form-control"
                                    value={password}
                                    onChange={e => setPassword(e.target.value)}
                                    required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row py-sm-1">
                            <label htmlFor="name" className="col-md-3 col-form-label">
                                <FormattedMessage id="Nombre"/>
                            </label>
                            <div className="col-md-4">
                                <input type="text" id="name" className="form-control"
                                    value={name}
                                    onChange={e => setName(e.target.value)}
                                    required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row py-sm-1">
                            <label htmlFor="surname" className="col-md-3 col-form-label">
                                <FormattedMessage id="Apellido"/>
                            </label>
                            <div className="col-md-4">
                                <input type="text" id="surname" className="form-control"
                                    value={surname}
                                    onChange={e => setSurname(e.target.value)}
                                    required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row py-sm-1">
                            <label htmlFor="email" className="col-md-3 col-form-label">
                                <FormattedMessage id="Correo"/>
                            </label>
                            <div className="col-md-4">
                                <input type="email" id="email" className="form-control"
                                    value={email}
                                    onChange={e => setEmail(e.target.value)}
                                    required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.email'/>
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

export default SignUp;