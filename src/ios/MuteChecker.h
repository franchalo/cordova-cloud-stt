#import <Foundation/Foundation.h>
#import <AudioToolbox/AudioToolbox.h>

typedef void (^MuteCheckCompletionHandler)(NSTimeInterval lapse, BOOL muted);

// this class must use with a MuteChecker.caf (a 0.2 sec mute sound) in Bundle
@interface MuteChecker : NSObject
-(instancetype)initWithCompletionBlk:(MuteCheckCompletionHandler)completionBlk;
-(void)check;
@end
