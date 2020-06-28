const Sequelize = require('sequelize');
// module.exports = new Sequelize('android_server_db', 'root', '', {
//   timezone: '+03:00',
//   host: 'localhost',
//   dialect: 'mysql',
//   logging: console.log
// });

module.exports = new Sequelize("mysql://b6637f2682a8f6:66154f1f@eu-cdbr-west-03.cleardb.net/heroku_7cf6e0f87c27146?reconnect=true");
