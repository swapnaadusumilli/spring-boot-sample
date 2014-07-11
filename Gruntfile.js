module.exports = function(grunt) {

    var srcDir = 'src/main/webapp/js';

    // Project configuration.
    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),
        srcDir: 'src/main/webapp/js',
        depsDir: 'src/main/webapp/jslib',
        jshint: {
            files: [
                'Gruntfile.js',
                '<%= srcDir %>/home-controller.js',
                '<%= srcDir %>/app.js'
            ],
            options: {
                multistr: true
            }
        },
        concat: {
            app: {
                src: [
                    '<%= srcDir %>/home-controller.js',
                    '<%= srcDir %>/app.js'
                ],
                dest: '<%= srcDir %>/app.concat.js'
            }
        },
        uglify: {
            build: {
                files: [{
                    src: '<%= srcDir %>/app.concat.js',
                    dest: '<%= srcDir %>/app.min.js'
                }]
            }
        }
    });

    // Load the plugin that provides the "uglify" task.
    grunt.loadNpmTasks('grunt-contrib-jshint');
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-uglify');

    // Default task(s).
    grunt.registerTask('default', ['jshint','concat','uglify']);

};