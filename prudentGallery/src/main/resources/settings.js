document.execute('/defaults/application/settings/')

applicationName = 'prudentGallery'
applicationDescription = 'This is a simple web gallery'
applicationAuthor = 'Adrian M&ouml;rchen'
applicationOwner = 'Adrian M&ouml;rchen'
applicationHomeURL = 'http://www.scrobble.me/'
applicationContactEmail = 'prudentGallery@rest.scrobble.me'

showDebugOnError = true

// IrfanView (Windows only)
//Packages.me.scrobble.images.ImageUtils.setImageScaler(new Packages.me.scrobble.images.scalers.IrfanViewImageScaler())

// ImageMagick (Windows version, Linux commands might differ)
//imagemagick_commands_thumbnail = 'cmd /C convert "@SOURCE@" -resize @WIDTH@x@HEIGHT@^^ -gravity center -extent @WIDTH@x@HEIGHT@ "@TARGET@"'
//imagemagick_commands_large = 'cmd /C convert "@SOURCE@" -resize @WIDTH@x@HEIGHT@ "@TARGET@"'
//Packages.me.scrobble.images.ImageUtils.setImageScaler(new Packages.me.scrobble.images.scalers.CommandLineImageScaler(imagemagick_commands_thumbnail,imagemagick_commands_large))

