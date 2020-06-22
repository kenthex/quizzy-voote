const Sequelize = require('sequelize');
module.exports = new Sequelize('android_server_db', 'root', '', {
  host: 'localhost',
  dialect: 'mysql'
});
