const Sequelize = require('sequelize');
module.exports = new Sequelize('android_server_db', 'root', '', {
  timezone: '+03:00',
  host: 'localhost',
  dialect: 'mysql',
  logging: console.log
});
