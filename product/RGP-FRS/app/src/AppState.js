
function isLoggedIn()
{
    return AppState.user !== null;
}

const AppState = {
    app: null,
    user: null,

    isLoggedIn: isLoggedIn
};

export default AppState;