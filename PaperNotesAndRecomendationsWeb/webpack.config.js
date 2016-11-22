/**
 * Created by ppetrov on 10/15/2016.
 */

var path = require('path');
var webpack = require('webpack');
var ExtractTextPlugin = require('extract-text-webpack-plugin');
const autoprefixer = require('autoprefixer');

var appRoot = 'src/main/js';

var cssLoaderConfig = {
    discardEmpty: true,
    sourceMap: true
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
                loader: ExtractTextPlugin.extract('style-loader', [
                    'css-loader?' + JSON.stringify(cssLoaderConfig),
                    'postcss-loader',
                    'sass-loader'
                ])
            },
            {
                test: /\.html$/,
                loader: 'raw-loader'
            },
            {
                test: /\.(jpe?g|png|gif)$/,
                exclude: /(node_modules)/,
                loader: 'url-loader',
                query: {
                    limit: 4096,
                    name: 'assets/images/[name].[ext]'
                }
            },
        ]
    },

    resolve: {
        extensions: ['', '.js', '.jsx'],
        root: [
            path.join(__dirname, appRoot, 'app'),
        ]
    },

    sassLoader: {
        includePaths: [
            path.resolve(__dirname, appRoot, 'app')
        ]
    },

    postcss: [autoprefixer({
        browsers: [
            'Chrome >= 10',
            'Firefox >= 25',
            'Safari >= 8'
        ]
    })],

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
