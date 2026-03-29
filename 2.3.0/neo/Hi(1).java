package neo;

import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Hi {
   private static final Logger LOGGER = LogManager.getLogger();
   private final Set<Hh> taskEntries = Sets.newLinkedHashSet();
   private final Set<Hh> executingTaskEntries = Sets.newLinkedHashSet();
   private final Wk profiler;
   private int tickCount;
   private int tickRate = 3;
   private int disabledControlFlags;

   public Hi(Wk profilerIn) {
      this.profiler = profilerIn;
   }

   public void addTask(int priority, Gi task) {
      this.taskEntries.add(new Hh(this, priority, task));
   }

   public void removeTask(Gi task) {
      Iterator<Hh> iterator = this.taskEntries.iterator();

      Hh entityaitasks$entityaitaskentry;
      Gi entityaibase;
      do {
         if (!iterator.hasNext()) {
            return;
         }

         entityaitasks$entityaitaskentry = (Hh)iterator.next();
         entityaibase = entityaitasks$entityaitaskentry.action;
      } while(entityaibase != task);

      if (entityaitasks$entityaitaskentry.using) {
         entityaitasks$entityaitaskentry.using = false;
         entityaitasks$entityaitaskentry.action.resetTask();
         this.executingTaskEntries.remove(entityaitasks$entityaitaskentry);
      }

      iterator.remove();
   }

   public void onUpdateTasks() {
      this.profiler.startSection("goalSetup");
      Iterator iterator;
      Hh entityaitasks$entityaitaskentry;
      if (this.tickCount++ % this.tickRate == 0) {
         iterator = this.taskEntries.iterator();

         label57:
         while(true) {
            do {
               while(true) {
                  if (!iterator.hasNext()) {
                     break label57;
                  }

                  entityaitasks$entityaitaskentry = (Hh)iterator.next();
                  if (entityaitasks$entityaitaskentry.using) {
                     break;
                  }

                  if (this.canUse(entityaitasks$entityaitaskentry) && entityaitasks$entityaitaskentry.action.shouldExecute()) {
                     entityaitasks$entityaitaskentry.using = true;
                     entityaitasks$entityaitaskentry.action.startExecuting();
                     this.executingTaskEntries.add(entityaitasks$entityaitaskentry);
                  }
               }
            } while(this.canUse(entityaitasks$entityaitaskentry) && this.canContinue(entityaitasks$entityaitaskentry));

            entityaitasks$entityaitaskentry.using = false;
            entityaitasks$entityaitaskentry.action.resetTask();
            this.executingTaskEntries.remove(entityaitasks$entityaitaskentry);
         }
      } else {
         iterator = this.executingTaskEntries.iterator();

         while(iterator.hasNext()) {
            entityaitasks$entityaitaskentry = (Hh)iterator.next();
            if (!this.canContinue(entityaitasks$entityaitaskentry)) {
               entityaitasks$entityaitaskentry.using = false;
               entityaitasks$entityaitaskentry.action.resetTask();
               iterator.remove();
            }
         }
      }

      this.profiler.endSection();
      if (!this.executingTaskEntries.isEmpty()) {
         this.profiler.startSection("goalTick");
         iterator = this.executingTaskEntries.iterator();

         while(iterator.hasNext()) {
            entityaitasks$entityaitaskentry = (Hh)iterator.next();
            entityaitasks$entityaitaskentry.action.updateTask();
         }

         this.profiler.endSection();
      }

   }

   private boolean canContinue(Hh taskEntry) {
      return taskEntry.action.shouldContinueExecuting();
   }

   private boolean canUse(Hh taskEntry) {
      if (this.executingTaskEntries.isEmpty()) {
         return true;
      } else if (this.isControlFlagDisabled(taskEntry.action.getMutexBits())) {
         return false;
      } else {
         Iterator var2 = this.executingTaskEntries.iterator();

         while(var2.hasNext()) {
            Hh entityaitasks$entityaitaskentry = (Hh)var2.next();
            if (entityaitasks$entityaitaskentry != taskEntry) {
               if (taskEntry.priority >= entityaitasks$entityaitaskentry.priority) {
                  if (!this.areTasksCompatible(taskEntry, entityaitasks$entityaitaskentry)) {
                     return false;
                  }
               } else if (!entityaitasks$entityaitaskentry.action.isInterruptible()) {
                  return false;
               }
            }
         }

         return true;
      }
   }

   private boolean areTasksCompatible(Hh taskEntry1, Hh taskEntry2) {
      return (taskEntry1.action.getMutexBits() & taskEntry2.action.getMutexBits()) == 0;
   }

   public boolean isControlFlagDisabled(int p_188528_1_) {
      return (this.disabledControlFlags & p_188528_1_) > 0;
   }

   public void disableControlFlag(int p_188526_1_) {
      this.disabledControlFlags |= p_188526_1_;
   }

   public void enableControlFlag(int p_188525_1_) {
      this.disabledControlFlags &= ~p_188525_1_;
   }

   public void setControlFlag(int p_188527_1_, boolean p_188527_2_) {
      if (p_188527_2_) {
         this.enableControlFlag(p_188527_1_);
      } else {
         this.disableControlFlag(p_188527_1_);
      }

   }
}
