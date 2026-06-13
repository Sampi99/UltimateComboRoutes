import React from 'react';
import {useSelector} from 'react-redux';
import {Link} from 'react-router-dom';
import {FormattedMessage} from 'react-intl';

import administrators from '../../administrators';

const Header = () => {
	
	const username = useSelector(administrators.selectors.getUsername);
    const admin = useSelector(administrators.selectors.getAdmin);

    return (

        <nav className="navbar navbar-expand-lg navbar-light bg-light border">
			<div className="container-fluid">
	            <Link className="navbar-brand" to="/">Ultimate Combo Routes</Link>
	            <button className="navbar-toggler" type="button" 
	                data-toggle="collapse" data-target="#navbarSupportedContent" 
	                aria-controls="navbarSupportedContent" aria-expanded="false" 
	                aria-label="Toggle navigation">
	                <span className="navbar-toggler-icon"></span>
	            </button>

				<div className="collapse navbar-collapse" id="navbarSupportedContent">
					
					{username ?
					
					<ul className="navbar-nav ms-auto">

						<li className="nav-item dropdown">
	                        <a className="nav-link dropdown-toggle" href="#" role="button" 
								data-bs-toggle="dropdown">
	                            {username}
	                        </a>
	                        <div className="dropdown-menu dropdown-menu-end">
	                            <Link className="dropdown-item" to="/administrators/update-profile">
	                                <FormattedMessage id="Editar mi perfil"/>
	                            </Link>
	                            <Link className="dropdown-item" to="/administrators/change-password">
	                                <FormattedMessage id="Cambiar mi contraseña"/>
	                            </Link>
 	                            <div className="dropdown-divider"></div>
	                            <Link className="dropdown-item" to="/administrators/logout">
	                                <FormattedMessage id="Cerrar sesión"/>
	                            </Link>
	                        </div>
	                    </li>
					</ul>
					
					:
					
					<ul className="navbar-nav ms-auto">
						<li className="nav-item">
	                        <Link className="nav-link" to="/administrators/login">
	                            <FormattedMessage id="Iniciar sesión"/>
	                        </Link>
	                    </li>
					</ul>
					
					}
				</div>
			</div>		
        </nav>

    );

};

export default Header;