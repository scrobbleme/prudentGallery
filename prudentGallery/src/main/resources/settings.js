document.execute('/defaults/application/settings/')

applicationName = 'prudentGallery'
applicationDescription = 'This is a simple web gallery'
applicationAuthor = 'Adrian M&ouml;rchen'
applicationOwner = 'Adrian M&ouml;rchen'
applicationHomeURL = 'http://www.scrobble.me/'
applicationContactEmail = 'prudentGallery@rest.scrobble.me'

showDebugOnError = true

Packages.me.scrobble.images.ImageUtils.setImageScaler(new Packages.me.scrobble.images.scalers.IrfanViewImageScaler())