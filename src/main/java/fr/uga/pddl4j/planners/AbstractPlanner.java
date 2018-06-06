/*
 * Copyright (c) 2016 by Damien Pellier <Damien.Pellier@imag.fr>.
 *
 * This file is part of PDDL4J library.
 *
 * PDDL4J is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * PDDL4J is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with PDDL4J.  If not, see
 * <http://www.gnu.org/licenses/>
 */

package fr.uga.pddl4j.planners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

/**
 * This abstract class defines the main methods to access a planner.
 *
 * @author D. Pellier
 * @version 1.0 - 12.04.2016
 * @since 3.0
 */
public abstract class AbstractPlanner implements Planner {

    /**
     * The enumeration of the arguments of the planner.
     */
    public enum Argument {
        /**
         * The planner to use.
         */
        PLANNER,
        /**
         * The planning domain.
         */
        DOMAIN,
        /**
         * The planning problem.
         */
        PROBLEM,
        /**
         * The global time slot allocated to the search.
         */
        TIMEOUT,
        /**
         * The trace level.
         */
        TRACE_LEVEL,
        /**
         * Generate statistics or not.
         */
        STATISTICS
    }

    /**
     * The timeout for the search in second.
     */
    private int timeout;

    /**
     * The trace level.
     */
    private int traceLevel;

    /**
     * The statistics of the planner.
     */
    private Statistics statistics;

    /**
     * The state to save the statistics of the planner.
     */
    private boolean saveState;

    /**
     * Creates a new planner.
     */
    public AbstractPlanner() {
        super();
        this.timeout = Planner.DEFAULT_TIMEOUT;
        this.traceLevel = Planner.DEFAULT_TRACE_LEVEL;
        this.saveState = Planner.DEFAULT_STATISTICS;
        this.statistics = new Statistics();
    }

    /**
     * Returns the statistics of the planner.
     *
     * @return the statistics of the planner or null if no problem was solved.
     * @see Statistics
     */
    @Override
    public final Statistics getStatistics() {
        return this.statistics;
    }

    /**
     * Sets the time out of the planner.
     *
     * @param timeout the time allocated to the search in second. Timeout mus be positive.
     */
    @Override
    public final void setTimeOut(final int timeout) {
        this.timeout = timeout;
    }

    /**
     * Returns the time out of the planner.
     *
     * @return the time out of the planner, i.e., the time allocated to the search in second.
     */
    @Override
    public int getTimeout() {
        return this.timeout;
    }

    /**
     * Sets the trace level of the planner.
     *
     * @param level the trace level of the planner. Trace level must be positive.
     */
    @Override
    public final void setTraceLevel(final int level) {
        this.traceLevel = level;
    }

    /**
     * Returns the trace level of the planner.
     *
     * @return the trace level of the planner.
     */
    @Override
    public final int getTraceLevel() {
        return this.traceLevel;
    }

    /**
     * Set the statistics generation value.
     *
     * @param saveState the new statistics computation value
     */
    @Override
    public void setSaveState(boolean saveState) {
        this.saveState = saveState;
    }

    /**
     * Is statistics generate or not.
     *
     * @return true if statistics are compute and save, false otherwise
     */
    @Override
    public boolean isSaveState() {
        return saveState;
    }

    /**
     * Returns the LOGGER of the AbstractPlanner class.
     *
     * @return the AbstractPlanner class.
     */
    public Logger getLogger() {
        return Planner.getLogger();
    }

    /**
     * This method return the default arguments of the planner.
     *
     * @return the default arguments of the planner.
     */
    public static Properties getDefaultArguments() {
        final Properties options = new Properties();
        options.put(AbstractPlanner.Argument.TIMEOUT, AbstractPlanner.DEFAULT_TIMEOUT * 1000);
        options.put(AbstractPlanner.Argument.TRACE_LEVEL, AbstractPlanner.DEFAULT_TRACE_LEVEL);
        options.put(AbstractPlanner.Argument.STATISTICS, AbstractPlanner.DEFAULT_STATISTICS);
        return options;
    }

}
