/*
	Cordova Text-to-Speech Plugin
	https://github.com/vilic/cordova-plugin-tts

	by VILIC VANE
	https://github.com/vilic

	MIT License
 */

#import <Cordova/CDV.h>
#import <AVFoundation/AVFoundation.h>
#import "MuteChecker.h"

@interface CDVTTS : CDVPlugin <AVSpeechSynthesizerDelegate> {
    AVSpeechSynthesizer* synthesizer;
    NSString* lastCallbackId;
    NSString* callbackId;
    MuteChecker *muteChecker;
}

- (void)speak:(CDVInvokedUrlCommand*)command;
- (void)stop:(CDVInvokedUrlCommand*)command;
- (void)checkDeviceMute:(CDVInvokedUrlCommand*)command;
@end
