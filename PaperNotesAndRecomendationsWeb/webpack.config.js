/**
 * Created by ppetrov on 10/15/2016.
 */

var path = require('path');

var webpack = require('webpack');

var appRoot = 'src/main/js';

var config = {
    devtool: 'source-map',

    context: path.join(__dirname, appRoot),

    entry: {
        index: './app/index.js'
    },

    module: {
        loaders: [
            {
                test: /\.json$/,
                loader: "json"
            },
            {
                test: /\.jsx?$/,
                exclude: /(node_modules)/,
                loader: 'babel-loader'
            },
            {
                test: /\.css$/,
                loader: 'style!css'
            },
            {
                test: /\.html$/,
                loader: 'raw-loader'
            }
        ]
    },

    resolve: {

        extensions: ['', '.js', '.jsx'],

        root: [
            path.join(__dirname, appRoot, 'app'),
        ]
    },

    output: {
        path: path.join(__dirname, appRoot, '/dist/js'),
        publicPath: 'http://localhost:8082/globalres/js/',
        filename: '[name].bundle.js'
    },

    devServer: {
        contentBase: "src/main/js/app",
        colors: true,
        port: 8082,
        historyApiFallback: true
    }
};

module.exports = config;
