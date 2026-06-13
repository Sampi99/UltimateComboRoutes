import {useEffect} from 'react';
import {useDispatch} from 'react-redux';
import {useHistory} from 'react-router-dom';

import administrators from '../../administrators';

const Logout = () => {

    const dispatch = useDispatch();
    const history = useHistory();

    useEffect(() => {
        dispatch(administrators.actions.logout());
        history.push('/');
    });

    return null;

}

export default Logout;