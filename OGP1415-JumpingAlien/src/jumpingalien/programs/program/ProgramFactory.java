package jumpingalien.programs.program;

import java.util.List;
import java.util.Map;

import jumpingalien.part3.programs.IProgramFactory;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.expressions.*;
import jumpingalien.programs.statements.Statement;
import jumpingalien.programs.types.Type;

public class ProgramFactory implements IProgramFactory<Expression, Statement, Type, Program> {

	@Override
	public Expression createReadVariable(String variableName,
			Type variableType, SourceLocation sourceLocation) {
		return new ReadVariable(variableName, variableType, sourceLocation);
	}

	@Override
	public Expression createDoubleConstant(double value,
			SourceLocation sourceLocation) {
		return new DoubleConstant(value, sourceLocation);
	}

	@Override
	public Expression createTrue(SourceLocation sourceLocation) {
		return new True(sourceLocation);
	}

	@Override
	public Expression createFalse(SourceLocation sourceLocation) {
		return new False(sourceLocation);
	}

	@Override
	public Expression createNull(SourceLocation sourceLocation) {
		return new Null(sourceLocation);
	}

	@Override
	public Expression createSelf(SourceLocation sourceLocation) {
		return new Self(sourceLocation);
	}

	@Override
	public Expression createDirectionConstant(
			jumpingalien.part3.programs.IProgramFactory.Direction value,
			SourceLocation sourceLocation) {
		return new DirectionConstant(value, sourceLocation);
	}

	@Override
	public Expression createAddition(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new Addition(left, right, sourceLocation);
	}

	@Override
	public Expression createSubtraction(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new Subtraction(left, right, sourceLocation);
	}

	@Override
	public Expression createMultiplication(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new Multiplication(left, right, sourceLocation);
	}

	@Override
	public Expression createDivision(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new Division(left, right, sourceLocation);
	}

	@Override
	public Expression createSqrt(Expression expr, SourceLocation sourceLocation) {
		return new Sqrt(expr,sourceLocation);
	}

	@Override
	public Expression createRandom(Expression maxValue,
			SourceLocation sourceLocation) {
		return new Random(maxValue, sourceLocation);
	}

	@Override
	public Expression createAnd(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new And(left, right, sourceLocation);
	}

	@Override
	public Expression createOr(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new Or(left, right, sourceLocation);
	}

	@Override
	public Expression createNot(Expression expr, SourceLocation sourceLocation) {
		return new Not(expr, sourceLocation);
	}

	@Override
	public Expression createLessThan(Expression left, Expression right,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createLessThanOrEqualTo(Expression left,
			Expression right, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGreaterThan(Expression left, Expression right,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGreaterThanOrEqualTo(Expression left,
			Expression right, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createEquals(Expression left, Expression right,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createNotEquals(Expression left, Expression right,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetX(Expression expr, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetY(Expression expr, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetWidth(Expression expr,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetHeight(Expression expr,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetHitPoints(Expression expr,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetTile(Expression x, Expression y,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createSearchObject(Expression direction,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createIsMazub(Expression expr,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createIsShark(Expression expr,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createIsSlime(Expression expr,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createIsPlant(Expression expr,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createIsDead(Expression expr,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createIsTerrain(Expression expr,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createIsPassable(Expression expr,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createIsWater(Expression expr,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createIsMagma(Expression expr,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createIsAir(Expression expr, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createIsMoving(Expression expr, Expression direction,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createIsDucking(Expression expr,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createIsJumping(Expression expr,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createAssignment(String variableName, Type variableType,
			Expression value, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createWhile(Expression condition, Statement body,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createForEach(
			String variableName,
			jumpingalien.part3.programs.IProgramFactory.Kind variableKind,
			Expression where,
			Expression sort,
			jumpingalien.part3.programs.IProgramFactory.SortDirection sortDirection,
			Statement body, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createBreak(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createIf(Expression condition, Statement ifBody,
			Statement elseBody, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createPrint(Expression value, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createStartRun(Expression direction,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createStopRun(Expression direction,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createStartJump(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createStopJump(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createStartDuck(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createStopDuck(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createWait(Expression duration,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createSkip(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createSequence(List<Statement> statements,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type getDoubleType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type getBoolType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type getGameObjectType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type getDirectionType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Program createProgram(Statement mainStatement,
			Map<String, Type> globalVariables) {
		// TODO Auto-generated method stub
		return null;
	}



}
