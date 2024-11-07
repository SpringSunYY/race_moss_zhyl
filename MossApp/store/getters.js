const getters = {
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  roles: state => state.user.roles,
  role: state => state.user.role,
  permissions: state => state.user.permissions
}
export default getters
