/**
 * Created by ppetrov on 10/15/2016.
 */

var path = require('path');

var appRoot = 'src/main/js';

module.exports = {
    devtool: 'cheap-module-source-map',

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
            }
        ]
    },

    output: {
        path: path.join(__dirname, appRoot, '/fin_dist/js'),
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

