/**
 * Created by ppetrov on 10/15/2016.
 */

var path = require('path');
var webpack = require('webpack');
var ExtractTextPlugin = require('extract-text-webpack-plugin');

var appRoot = 'src/main/js';

var shouldUseSourceMaps = true;
var cssLoaderConfig = {
    discardEmpty: true,
    sourceMap: shouldUseSourceMaps
};

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
                test: /\.s?css$/,
                exclude: /(node_modules)/,
                loader: ExtractTextPlugin.extract('style-loader', [
                    'css-loader?' + JSON.stringify(cssLoaderConfig),
                    'postcss-loader',
                    'sass-loader' + (shouldUseSourceMaps ? '?sourceMap' : '')
                ])
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

    plugins: [
        new ExtractTextPlugin('[name].css'),
    ],

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
