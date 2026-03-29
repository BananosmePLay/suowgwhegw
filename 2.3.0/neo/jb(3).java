package neo;

import paulscode.sound.SoundSystem;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.Source;

class jb extends SoundSystem {
   // $FF: synthetic field
   final jc this$0;

   private jb(jc this$0) {
      this.this$0 = this$0;
   }

   public boolean playing(String p_playing_1_) {
      synchronized(SoundSystemConfig.THREAD_SYNC) {
         if (this.soundLibrary == null) {
            return false;
         } else {
            Source source = (Source)this.soundLibrary.getSources().get(p_playing_1_);
            if (source == null) {
               return false;
            } else {
               return source.playing() || source.paused() || source.preLoad;
            }
         }
      }
   }

   // $FF: synthetic method
   jb(jc x0, Object x1) {
      this(x0);
   }
}
