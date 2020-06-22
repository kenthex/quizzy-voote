const Sequelize = require('sequelize');
const db = require('../config/database');
const User = require('../models/User');

const Token = db.define('tokens', {
  id: {
    type: Sequelize.INTEGER(),
    primaryKey: true,
    autoIncrement: true,
    unique: true,
    allowNull: false
  },

  user_id: {
    type: Sequelize.INTEGER(),
    allowNull: false
  },

  token: {
    type: Sequelize.STRING(),
    unique: true,
    allowNull: false
  },

  expired_at: {
    type: Sequelize.DATE(),
    allowNull: false
  }

});

module.exports = Token;
