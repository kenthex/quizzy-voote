const Sequelize = require('sequelize');
const db = require('../config/database');
const Vote = require('../models/Vote');
const Answer = require('../models/Answer');
const User = require('../models/Answer');

const Question = db.define('questions', {
  id: {
    type: Sequelize.INTEGER(),
    primaryKey: true,
    autoIncrement: true,
    unique: true,
    allowNull: false
  },

  creator: {
    type: Sequelize.INTEGER(),
    allowNull: false
  },

  title: {
    type: Sequelize.STRING(),
    unique: true,
    allowNull: false
  },

  expired_at: {
    type: Sequelize.DATE(),
    allowNull: false
  },

  verified: {
    type: Sequelize.INTEGER,
    allowNull: false
  }

});

Question.hasOne(Vote, { foreignKey: 'question_id' });
Vote.belongsTo(Question, { foreignKey: 'question_id' });

Question.hasMany(Answer, { foreignKey: 'question_id' });
Answer.belongsTo(Question, { foreignKey: 'question_id' });

module.exports = Question;
