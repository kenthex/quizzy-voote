const Sequelize = require('sequelize');
const db = require('../config/database');

const Answer = db.define('answers', {
  id: {
    type: Sequelize.INTEGER(),
    primaryKey: true,
    autoIncrement: true,
    unique: true,
    allowNull: false
  },

  question_id: {
    type: Sequelize.INTEGER(),
    allowNull: false
  },

  title: {
    type: Sequelize.STRING(),
    allowNull: false
  },

});

module.exports = Answer;
